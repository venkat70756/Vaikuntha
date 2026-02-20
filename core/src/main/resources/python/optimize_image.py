import sys
import os
from PIL import Image


def optimize_image(input_path, output_path):
    original_size = os.path.getsize(input_path)

    with Image.open(input_path) as img:
        img_format = img.format.upper()

        # -------------------------
        # PNG (Lossless)
        # -------------------------
        if img_format == "PNG":
            img.save(
                output_path,
                format="PNG",
                optimize=True,
                compress_level=9
            )

        # -------------------------
        # JPEG (Safe Optimization)
        # -------------------------
        elif img_format in ["JPEG", "JPG"]:
            img = img.convert("RGB")

            img.save(
                output_path,
                format="JPEG",
                quality=82,      # Safe visual level
                optimize=True,
                subsampling=2    # 4:2:0 web standard
            )

        else:
            # Fallback: copy as-is
            img.save(output_path, format=img_format)

    optimized_size = os.path.getsize(output_path)

    # ---------------------------------
    # IMPORTANT: Never increase size
    # ---------------------------------
    if optimized_size >= original_size:
        # Keep original instead
        os.remove(output_path)
        print("NO_SIZE_IMPROVEMENT")
    else:
        print(f"SIZE_REDUCED: {original_size} -> {optimized_size}")


def main():
    if len(sys.argv) != 3:
        print("Usage: optimize_image.py <input> <output>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]

    if not os.path.exists(input_path):
        print(f"Input file not found: {input_path}")
        sys.exit(1)

    try:
        optimize_image(input_path, output_path)
        print("IMAGE_OPTIMIZATION_SUCCESS")
    except Exception as e:
        print(f"IMAGE_OPTIMIZATION_FAILED: {e}")
        sys.exit(1)


if __name__ == "__main__":
    main()
